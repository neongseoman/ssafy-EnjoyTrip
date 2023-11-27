import { computed, ref, watch } from "vue";
import { defineStore } from "pinia";

export const useBoardStore = defineStore(
    "board",
    () => {
        const isupdated = ref('NO');
        const updateBoard = function (is) {
            isupdated.value = is;
            console.log(is);
        }
        const watchBoard = computed(function () {
            return isupdated;
        })
        return {
            isupdated,
            updateBoard, watchBoard
        };
    },
    { persist: { storage: sessionStorage } }
)