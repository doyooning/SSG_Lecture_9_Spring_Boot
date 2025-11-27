<script setup>
import {reactive} from "vue";
import {join} from "@/services/accountService";
import {useRouter} from "vue-router";

// 반응형 상태
const state = reactive({
  form: {
    name: "",
    loginId: "",
    loginPw: "",
  },
  errors: {
    name: "",
    loginId: "",
    loginPw: "",
  },
});

// 라우터 객체
const router = useRouter();

/* 정규식 및 입력 데이터 검증 시작 */
// 이메일 정규식
const idRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

// 비밀번호: 8자 이상, 숫자+문자 포함
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

const validateForm = () => {
  state.errors = { name: "", loginId: "", loginPw: "" };
  let isValid = true;

  // 이름
  if (!state.form.name.trim()) {
    state.errors.name = "이름을 입력해주세요.";
    isValid = false;
  }

  // 아이디
  if (!idRegex.test(state.form.loginId)) {
    state.errors.loginId = "올바른 이메일 형식이 아닙니다.";
    isValid = false;
  }

  // 비밀번호
  if (!passwordRegex.test(state.form.loginPw)) {
    state.errors.loginPw = "비밀번호는 8자 이상, 문자와 숫자를 포함해야 합니다.";
    isValid = false;
  }

  return isValid;
};
/* 정규식 및 입력 데이터 검증 끝 */

// 회원가입 데이터 제출
const submit = async () => {
  if (!validateForm()) {
    return;
  }
  const res = await join(state.form);

  if (res.status === 200) {
    window.alert("회원가입을 완료했습니다.");
    await router.push("/");
  } else if (res.status === 409) {
    window.alert("이미 존재하는 이메일입니다.");
  }
};
</script>

<template>
  <div class="join">
    <div class="container">
      <!-- Form Start -->
      <form class="py-5 d-flex flex-column gap-3" @submit.prevent="submit">
        <!--  회원가입  -->
        <h1 class="h5 mb-3">회원가입</h1>
        <div class="form-floating">
          <input type="text" class="form-control" id="name" placeholder="이름" v-model="state.form.name">  <!-- ⑥ -->
          <label for="name">이름</label>
        </div>
        <p class="text-danger small">{{ state.errors.name }}</p>

        <div class="form-floating">
          <input type="email" class="form-control" id="loginId" placeholder="이메일" v-model="state.form.loginId"> <!-- ⑥ -->
          <label for="loginId">이메일</label>
        </div>

        <p class="text-danger small">{{ state.errors.loginId }}</p>
        <div class="form-floating">
          <input type="password" class="form-control" id="loginPw" placeholder="패스워드" v-model="state.form.loginPw"> <!-- ⑥ -->
          <label for="loginPw">패스워드</label>
        </div>
        <p class="text-danger small">{{ state.errors.loginPw }}</p>
        <!--  폼 제출 버튼  -->
        <button type="submit" class="w-100 h6 btn py-3 btn-primary">회원가입</button> <!-- ⑦ -->
      </form>
      <!-- Form End -->
    </div>
  </div>
</template>

<style lang="scss" scoped>
.join > .container { // ⑧
  max-width: 576px;
}
</style>