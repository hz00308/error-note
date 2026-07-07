const dateFormatter = new Intl.DateTimeFormat('ko-KR', {
  year: 'numeric',
  month: 'short',
  day: 'numeric',
})

export function formatDate(value) {
  if (!value) return '-'
  return dateFormatter.format(new Date(value))
}

export function formatDateTime(value) {
  if (!value) return '-'
  return dateFormatter.format(new Date(value))
}
