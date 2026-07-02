import apiClient from './axios'

export async function getErrors(categoryId) {
  const config = categoryId ? { params: { cid: categoryId } } : undefined
  const { data } = await apiClient.get('/errors', config)
  return data
}

export async function getError(errorId) {
  const { data } = await apiClient.get(`/errors/${errorId}`)
  return data
}

export async function createError(payload) {
  const { data } = await apiClient.post('/errors', payload)
  return data
}

export async function updateError(errorId, payload) {
  const { data } = await apiClient.put(`/errors/${errorId}`, payload)
  return data
}

export async function deleteError(errorId) {
  const { data } = await apiClient.delete(`/errors/${errorId}`)
  return data
}
