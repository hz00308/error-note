import { readonly, ref } from 'vue'

const toasts = ref([])
let nextId = 0

function removeToast(id) {
  toasts.value = toasts.value.filter((toast) => toast.id !== id)
}

function showToast(message, type = 'success') {
  const id = ++nextId
  toasts.value.push({ id, message, type })
  window.setTimeout(() => removeToast(id), 3200)
}

export function useToast() {
  return {
    toasts: readonly(toasts),
    showToast,
    removeToast,
  }
}
