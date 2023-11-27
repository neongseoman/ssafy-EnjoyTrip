<script setup>
import {ip} from '@/util/ip.js'
import { inject, ref, watch } from "vue";
import { getCookie, deleteCookie} from "@/assets/js/cookie";
// import { isLogin } from "../../../../src/assets/js/logout";
const showDetailModal = inject('open_modal');
const openDetailModal = inject('close_modal_function');

const mapInfo = inject('map_info');

const desc = ref({});
watch(mapInfo, function () {
    console.log(mapInfo.value.content_id);
    fetch(ip + '/MytfEaEE/Lnbkmq9x', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({content_id: mapInfo.value.content_id,  sessionId: getCookie('sessionId') })
    }).then(function (res) {
        if (res.statusCode === 999) {
            throw new Error("x");
        }
        return res.json();
    }).then(function (result) {
        if (result['msg'] == 'OK') {
            desc.value = result['desc'];
        }        
    }).catch(err => {
        console.log(err);
        isLogin.value = false;
        deleteCookie('sessionId');
        location.reload();
    })
});


</script>

<template>
    <Transition name="modal">
        <div v-if="showDetailModal" class="modal-mask">
            <div class="modal-container">
                <div class="modal-header">
                    <header id="login-modal__modal_header_" class="modal-header">
                        <h3 id="login-modal__modal_title_" class="modal-title">{{ mapInfo.title }}</h3>
                    </header>
                </div>

                <div class="modal-body">
                    <div id="login-modal___modal_body_" class="modal-body">
                        <div class="container-fluid">
                            <div class="row mb-1">
                                <div class="col-12 col-md-3 text-md-right">
                                    <label>설명</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    {{desc.overview}}
                                </div>
                            </div>
                            <div class="row mb-1">
                                <div class="col-12 col-md-3 text-md-right">
                                    <label>전화 번호</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    {{mapInfo.tel}}
                                </div>
                            </div>
                            <div class="row mb-1">
                                <div class="col-12 col-md-3 text-md-right">
                                    <label>홈페이지</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    {{desc.homepage}}
                                </div>
                            </div>
                            <div class="row mb-1">
                                <div class="col-12 col-md-3 text-md-right">
                                    <label>주소</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    {{mapInfo.addr1}}{{ mapInfo.addr2 }}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <footer>
                        <div>
                            <!--              @click="$emit('close')"-->
                            <button class="modal-default-button" @click=openDetailModal() id="cancel-button">닫기
                            </button>
                        </div>
                    </footer>
                </div>
            </div>
        </div>
    </Transition>
</template>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  transition: opacity 0.3s ease;
}

.modal-container {
  width: 500px;
  margin: auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>