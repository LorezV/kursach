import axios from '../axios'
import { defineStore } from 'pinia'

export type TItem = {
  id: number
  name: string
}

export type TForm = {
  name: string
}

export default defineStore('category', {
  state: () => {
    return {
      data: [],
    } as { data: TItem[] }
  },
  actions: {
    fetchData() {
      axios
        .get('/category')
        .then((response) => {
          if (response.status == 200) {
            this.data = response.data
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },
    create(form: TForm) {
      axios
        .post('category', form)
        .then((response) => {
          if (response.status == 200) {
            this.fetchData()
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },
    update(id: number, form: TForm) {
      axios
        .put(`category/${id}`, form)
        .then((response) => {
          if (response.status == 200) {
            this.fetchData()
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },

    remove(id: number) {
      axios
        .delete(`category/${id}`)
        .then((response) => {
          if (response.status == 200) {
            this.fetchData()
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },
  },
})
