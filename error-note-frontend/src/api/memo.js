import apiClient from './axios'

export async function getMemos(errorId) {
  const { data } = await apiClient.get(`/memos/${errorId}`)
  return data
}

export async function createMemo(payload) {
  const { data } = await apiClient.post('/memos', payload)
  return data
}

export async function deleteMemo(memoId) {
  const { data } = await apiClient.delete(`/memos/${memoId}`)
  return data
}
