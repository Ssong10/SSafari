<template>
  <v-container >
    <v-row align="center">
      <v-col cols="3">
        <v-select
          label="CodeType"
          :items="items"
          item-text="text"
          item-value="val"
          v-model="type"
          color="#ffc837"
        ></v-select>
      </v-col>
      <v-spacer />
      <v-col cols="1">
        <label col-1> 제목 :</label>
      </v-col>
      <v-col cols="6">
        <v-text-field col-8 v-model="title" color="#ffc837"></v-text-field>
      </v-col>
    </v-row>
    <textarea class="code-textarea" v-model="body" placeholder="내용을 입력하세요." rows="6" color="#ffc837"></textarea>
    <codemirror
      ref="myCm"
      :value="code"
      :options="cmOptions"
      @ready="onCmReady"
      @focus="onCmFocus"
      @input="onCmCodeChange"
    ></codemirror>
    <div class="d-flex justify-end mt-3">
      <v-checkbox v-model="anonymousStatus" label="익명" value="익명" class="annoyCheck"/>
      <v-btn class="ml-4" color="#f7b157" small @click="codecreate()">작성하기</v-btn>
    </div>
  </v-container>
</template>

<script>
import router from '@/router'
import CodeMirror from 'codemirror'
import { codemirror } from 'vue-codemirror'
import axios from 'axios'
import 'codemirror/lib/codemirror.css'
import 'codemirror/mode/xml/xml.js'
import 'codemirror/mode/javascript/javascript.js'
import 'codemirror/theme/base16-dark.css'
import 'codemirror/mode/python/python.js'

export default {
  components: {
    codemirror
  },
  props : {
    id : {type: String }
  },
  data () {
    return {
      body : '',
      anonymousStatus : null,
      codes : [],
      title : '',
      items : [
        { text : 'Python', val : 'text/x-python'},
        { text : 'Java', val : 'text/x-java'},
        { text : 'JavaScript', val : 'text/javascript'},
        { text : 'Html', val : 'text/html'},
        { text :'C++' , val : 'text/x-c++src'} ],
      type : '',
      code: '',
      cmOptions: {
        tabSize: 4,
        mode: 'text/x-python',
        lineNumbers: true,
        line: true,
      }
    }
  },
  mounted() {
    if (this.id){
      this.getcode()
    }
  },
  methods: {
    getcode() {
        axios.get(`api/code/${this.id}`)
        .then(response => {
        this.code = response.data.code.code
        this.title = response.data.code.title
        this.body = response.data.code.body
        this.anonymousStatus = response.data.code.anonymous ? '익명' : null
        this.type = response.data.code.lang
        })
    },
    onCmReady(cm) {
      cm.options.mode = this.type
    },
    onCmFocus(cm) {
      cm.options.mode = this.type
    },
    onCmCodeChange(newCode) {
      this.code = newCode
    },
    codecreate() {
      const formData = new FormData()
      const data = {
        'title' : this.title,
        'body' : this.body,
        'code' : this.code,
        'lang' : this.type,
        'anonymous' : this.anonymousStatus ? 1 : 0,
        'memberid' : this.$store.state.memberid,
      }
      if (this.$route.name==="codecreate"){
        axios.post('/api/code',data)
          .then(response =>{
            router.push({ path: '/board/code/' })
          })
          .catch(error => {
            console.log(error)
          })
      } else if (this.$route.name ==="codeedit") {
        data['id'] = this.id
        axios.put(`/api/code`,data)
          .then(response =>{
            router.push({ path: `/board/code/${this.id}` })
          })
          .catch(error => {
            console.log(error)
          })
      }
    },
  },
  computed: {
    codemirror() {
      return this.$refs.myCm.codemirror
    }
  },
  watch: {
    type: function(type) {
      this.cmOptions.mode = type
    },
  }
}
</script>

<style>
  .code-textarea {
    width: 100%;
    resize: none;
    border: 1px solid lightgray;
  }
</style>