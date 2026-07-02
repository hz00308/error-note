import apiClient from './axios'

export async function getCategories() {
  const { data } = await apiClient.get('/categories')
  return data
}

export async function getCategory(categoryId) {
  const { data } = await apiClient.get(`/categories/${categoryId}`)
  return data
}

export async function createCategory(payload) {
  const { data } = await apiClient.post('/categories', payload)
  return data
}

export async function updateCategory(categoryId, payload) {
  const { data } = await apiClient.put(`/categories/${categoryId}`, payload)
  return data
}

export async function deleteCategory(categoryId) {
  const { data } = await apiClient.delete(`/categories/${categoryId}`)
  return data
}
