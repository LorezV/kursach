import axios from '../axios'
import { defineStore } from 'pinia'

export type TItem = {
  id: number
  name: string
  phone: string
  address: string
  discountCard?: string
}

export default defineStore('customer', {
  state: () => {
    return {
      data: [] as TItem[],
    }
  },
  actions: {
    fetchData() {
      axios
        .get('/customer')
        .then((response) => {
          if (response.status == 200) {
            this.data = response.data
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },
  },
})
