import axios from '../axios'
import { defineStore } from 'pinia'

export type TItem = {
  id: number
  name: string
  weight: number
  price: number
  category: {
    id: number
    name: string
  }
}

export type TForm = {
  name: string
  price: number
  weight: number
  category: {
    id: number
  }
}

export default defineStore('product', {
  state: () => {
    return {
      data: [],
    }
  },
  actions: {
    fetchData() {
      axios
        .get('/product')
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
        .post('product', form)
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
      console.log(JSON.stringify(form))
      axios
        .put(`product/${id}`, form)
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
        .delete(`product/${id}`)
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
