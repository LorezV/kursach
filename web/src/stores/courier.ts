import axios from '../axios'
import { defineStore } from 'pinia'

export type TItem = {
  id: number
  name: string
}

export default defineStore('courier', {
  state: () => {
    return {
      data: [] as TItem[],
    }
  },
  actions: {
    fetchData() {
      axios
        .get('/courier')
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
