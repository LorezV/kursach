import axios from '../axios'
import { defineStore } from 'pinia'

export type TItem = {
  id: number
  price: number
  ready: boolean,
  customer: {
    name: string
    phone: string
    address: string
    discountCard: string
  }
  courier?: {
    name: string
  }
  orderProducts: {
    quantity: number,
    product: {
        name: string
        price: number
    }
  }[]
}

export default defineStore('order', {
  state: () => {
    return {
      data: [] as TItem[],
    }
  },
  actions: {
    fetchData() {
      axios
        .get('/order')
        .then((response) => {
          if (response.status == 200) {
            this.data = (response.data as TItem[])
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },
  },
})
