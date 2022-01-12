<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png" @click="mymethod1">
    <HelloWorld msg="Welcome to Your Vue.js App" />
    <div ref="file"></div>
    <button
        size="mini"
        type="text"
        icon="el-icon-edit"
        @click="handleDownload()"
    >下载</button>
  </div>

</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import axios from 'axios'
const docx = require('docx-preview');
window.JSZip = require('jszip')


export default {
  name: 'App',
  components: {
    HelloWorld
  },
  methods:{
    mymethod1() {
      axios({
        method: 'get',
        responseType: 'blob', // 设置响应文件格式
        url: 'http://localhost:8081/file/fileuse',
      }).then(({data}) => {
        docx.renderAsync(data, this.$refs.file) // 渲染到页面预览
      })
      // axios.get("http://localhost:8081/file/fileuse").then((response) => {
      //   console.log(response.data)
      // })
    },
    // 文件下载处理
    handleDownload() {
      const img = document.createElement('img')
      // img.setAttribute('download', name + suffix)
      // img.setAttribute('target', '_blank')
      img.setAttribute('src', "http://localhost:8081/file/fileuse")
      img.setAttribute('alt', "无内容")
      document.getElementById("app").append(img)
      // img.click()
    }

  }

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
