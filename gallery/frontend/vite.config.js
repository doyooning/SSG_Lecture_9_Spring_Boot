import {fileURLToPath, URL} from 'node:url'
import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// CORS 문제 발생
// vite.config.js 파일에 서버객체에 Proxy 를 추가하여 /v1/api 로 시작하는 URL 경로는 서버에게 HTTP 요청을 하도록 우회시킨다
export default defineConfig({
  server: {
    proxy: {
      "/v1/api": {
        target: "http://localhost:8080",
        changeOrigin: true
      }
    }
  },
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
});