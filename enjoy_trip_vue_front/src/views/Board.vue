<script setup>
import { provide, ref} from 'vue';
const article_no = ref(0);
const update = ref(false);
function setArticle_no(no) {
  article_no.value = no;
}
provide('article_no', article_no);
</script>

<template>
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">여행 정보 공유</mark>
          </h2>
        </div>   
        <router-view @article-no="setArticle_no"></router-view>
    </div>
    
</template>

<style scoped>

</style>