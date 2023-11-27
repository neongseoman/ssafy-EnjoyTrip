import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useTodoStore = defineStore(
  "todo",
  () => {
    let id = ref(0);
    let todos = ref([]);

    const allTodosCount = computed(() => todos.value.length);
    const completedTodosCount = computed(() => todos.value.filter((todo) => todo.completed).length);
    const activeTodosCount = computed(() => todos.value.filter((todo) => !todo.completed).length);

    const addTodo = (title, date, time, user_id, completed) => {
      todos.value.push({ id: id.value++, title, time, date, completed, user_id});
      todos.value.sort(function (a, b) {
        const adate = a.date.split('-');
        const bdate = b.date.split('-');
        const atime = a.time.split(':');
        const btime = b.time.split(':');
        if (adate[0] == bdate[0]) {
          if (adate[1] == bdate[1]) {
            if (adate[2] == bdate[2]) {
              if (atime[0] == btime[0]) {
                if (atime[1] == btime[1]) {
                  return atime[2] - btime[2];
                }
                return atime[1] - btime[1];
              }
              return atime[0] - btime[0];
            }
            return adate[2] - bdate[2];
          }
          return adate[1] - bdate[1];
        }
        return adate[0] - bdate[0];
      });
    };

    function refreshTodo() {
      todos.value = [];
      id.value = 0;
    }

    const changeTodoComplete = (id) => {
      todos.value = todos.value.map((todo) =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      );
    };

    const removeTodo = (id) => {
      todos.value = todos.value.filter((todo) => todo.id !== id);
    };

    return {
      id,
      todos,
      allTodosCount,
      completedTodosCount,
      activeTodosCount,
      addTodo,
      refreshTodo,
      changeTodoComplete,
      removeTodo,
    };
  },
  { persist: { storage: localStorage } }
  // { persist: { storage: sessionStorage } }
);
