// Pinia를 사용하여 계정 스토어 구현
import {defineStore} from 'pinia'
// 계정 데이터를 다루는 계정 스토어
export const useAccountStore = defineStore("account", { // ① 스토어 고유 ID
    state: () => ({
        checked: false, // ② 사용자의 로그인 체크 여부 프로퍼티
        loggedIn: false, // ③ 사용자의 로그인 여부 프로퍼티
    }),
    actions: {
        setChecked(val) { // ④ 사용자의 로그인 체크 여부 값을 수정하는 메서드
            this.checked = val;
        },
        setLoggedIn(val) { // ⑤ 사용자의 로그인 여부 값을 수정하는 메서드
            this.loggedIn = val;
        },
    },
});