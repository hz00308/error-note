import axios from 'axios'

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
})

apiClient.interceptors.response.use(
  (response) => {
    const contentType = response.headers['content-type'] || ''
    if (contentType.includes('text/html')) {
      return Promise.reject({
        response,
        userMessage: '서버에서 요청을 처리하지 못했어요.',
      })
    }
    return response
  },
  (error) => {
    const message =
      error.response?.data?.message ||
      (error.response?.status >= 500
        ? '서버에서 요청을 처리하지 못했어요.'
        : '요청을 처리하는 중 문제가 생겼어요.')

    return Promise.reject(Object.assign(error, { userMessage: message }))
  },
)

export default apiClient
