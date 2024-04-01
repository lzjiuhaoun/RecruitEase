<template>
  <div class="content-list">
    <div>
      <div class="list-title">我的简历</div>
      <a-spin :spinning="loading" style="min-height: 200px;">
        <div class="list-content">
          <div class="edit-view">
            <div class="item flex-view">
              <div class="label required">真实姓名</div>
              <div class="right-box">
                <input type="text" v-model="tData.form.name" placeholder="请输入姓名" maxlength="20" class="input-dom">
                <p class="tip">支持中英文，长度不能超过 20 个字符</p>
              </div>
            </div>
            <div class="item flex-view">
              <div class="label required">简历文件</div>
              <div class="right-box" style="display: flex;flex-direction: row;">
                <a-upload name="file1" :multiple="false" :before-upload="beforeRawUpload" :show-upload-list="showUpload">
                  <a-button>
                    <upload-outlined></upload-outlined>
                    点击选择文件
                  </a-button>
                  （请上传 pdf 文件）
                </a-upload>
                <a v-if="tData.form.raw" :href="tData.form.downloadUrl" style="margin-top: 6px;">下载</a>
              </div>
            </div>
            <button class="save mg" @click="submit()">保存</button>
          </div>
        </div>
      </a-spin>
    </div>
  </div>
</template>

<script setup>
import { message } from "ant-design-vue";
import {
  detailApi, createApi, updateApi
} from '/@/api/resume'
import { BASE_URL } from "/@/store/constants";
import { useUserStore } from "/@/store";
import AvatarIcon from '/@/assets/images/avatar.jpg'

const router = useRouter();
const userStore = useUserStore();

let loading = ref(false)
let tData = reactive({
  form: {
    id: undefined,
    name: undefined,
    sex: undefined,
    birthday: undefined,
    education: undefined,
    cover: undefined,
    coverFile: undefined,
    rawFile: undefined,
    raw: undefined,
    email: undefined,
    mobile: undefined,
    description: undefined,
    downloadUrl: undefined,
  }
})

/*data: 
birthday: null
cover: "a41e5540-0349-4daf-a9b9-8e401c2cef4e.jpeg"
coverFile: null
education: null
email: null
id: 12
mobile: null
name: "mini"
raw: null
rawFile: null
school: null
sex: null
userId: "12" */

let showUpload = true

onMounted(() => {
  getDetail()
})


const beforeRawUpload = (file) => {
  // 改文件名
  showUpload = true
  const fileName = new Date().getTime().toString() + '.' + file.type.substring(12)
  const copyFile = new File([file], fileName)
  console.log(copyFile)
  if (copyFile) {
    tData.form.rawFile = copyFile
  }
  return false
}

const getDetail = () => {
  loading.value = true
  let userId = userStore.user_id
  detailApi({ userId: userId }).then(res => {
    if (res.data) {
      tData.form = res.data
      if (tData.form.raw !== undefined) {
        tData.form.downloadUrl = BASE_URL + "/api/staticfiles/resume/raw/" + tData.form.raw
      }
    }

    loading.value = false
  }).catch(err => {
    console.log(err)
    loading.value = false
  })
}
const submit = () => {
  let formData = new FormData()
  let userId = userStore.user_id
  formData.append('userId', userId)

  if (tData.form.id) {
    formData.append('id', tData.form.id)
  }

  if (!tData.form.name) {
    message.warn("请填写真实姓名")
    showUpload = false
    return
  }
  if (!tData.form.rawFile) {
    message.warn("请上传简历文件")
    return
  }


  formData.append('rawFile', tData.form.rawFile)
  if (tData.form.rawFile.name.substr(-3, 3) !== "pdf") {
    // console.log(tData.form.rawFile.name.substr(-3, 3))
    message.warn("上传的文件不为 pdf 文件，保存失败")
    showUpload = false
    return
  }

  formData.append('name', tData.form.name)

  if (tData.form.id) {
    updateApi(formData).then(res => {
      message.success('保存成功')
      getDetail()
    }).catch(err => {
      console.log(err)
    })
  } else {
    createApi(formData).then(res => {
      message.success('保存成功')
      getDetail()
    }).catch(err => {
      console.log(err)
    })

    showUpload = false;
  }
}
</script>

<style scoped lang="less">
input,
textarea {
  border-style: none;
  outline: none;
  margin: 0;
  padding: 0;
}

.flex-view {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
}

.required::before {
    color: #ff4d4f;
    content: "*";
    display: inline-block;
    font-family: SimSun,sans-serif;
    font-size: 14px;
    line-height: 1;
    margin-right: 4px;
}

.content-list {
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;

  .list-title {
    color: #152844;
    font-weight: 600;
    font-size: 18px;
    line-height: 48px;
    height: 48px;
    margin-bottom: 4px;
    border-bottom: 1px solid #cedce4;
  }

  .edit-view {
    .item {
      -webkit-box-align: center;
      -ms-flex-align: center;
      align-items: center;
      margin: 24px 0;

      .label {
        width: 100px;
        color: #152844;
        font-weight: 600;
        font-size: 14px;
      }

      .right-box {
        -webkit-box-flex: 1;
        -ms-flex: 1;
        flex: 1;
      }

      .avatar {
        width: 64px;
        height: 64px;
        border-radius: 50%;
        margin-right: 16px;
      }

      .change-tips {
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        -ms-flex-wrap: wrap;
        flex-wrap: wrap;
      }

      label {
        color: #4684e2;
        font-size: 14px;
        line-height: 22px;
        height: 22px;
        cursor: pointer;
        width: 100px;
        display: block;
      }

      .tip {
        color: #6f6f6f;
        font-size: 14px;
        height: 22px;
        line-height: 22px;
        margin: 0;
        width: 100%;
      }

      .right-box {
        -webkit-box-flex: 1;
        -ms-flex: 1;
        flex: 1;
      }

      .input-dom {
        width: 400px;
      }

      .input-dom {
        background: #f8fafb;
        border-radius: 4px;
        height: 40px;
        line-height: 40px;
        font-size: 14px;
        color: #152844;
        padding: 0 12px;
      }

      .tip {
        font-size: 12px;
        line-height: 16px;
        color: #6f6f6f;
        height: 16px;
        margin-top: 4px;
      }

      .intro {
        resize: none;
        background: #f8fafb;
        width: 100%;
        padding: 8px 12px;
        height: 82px;
        line-height: 22px;
        font-size: 14px;
        color: #152844;
      }
    }

    .save {
      background: #4684e2;
      border-radius: 32px;
      width: 96px;
      height: 32px;
      line-height: 32px;
      font-size: 14px;
      color: #fff;
      border: none;
      outline: none;
      cursor: pointer;
    }

    .mg {
      margin-left: 100px;
    }
  }
}
</style>
