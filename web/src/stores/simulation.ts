import axios from '../axios'
import { defineStore } from 'pinia'

type TItem = {
  duration: number
  steps: number
  step: number
  couriers: number
  status: boolean
}

export default defineStore('simulation', {
  state: () => {
    return {
      item: {
        duration: 30,
        steps: 0,
        step: 1,
        couriers: 5,
        status: false,
      } as TItem,
    }
  },
  actions: {
    fetchData() {
      axios
        .get<TItem>('simulation/status')
        .then((response) => {
          if (response.status == 200) {
            this.item = response.data
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },

    start() {
      axios
        .post('/simulation/start', this.item)
        .then((response) => {
          if (response.status == 200) {
            this.fetchData()
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },

    stop() {
      axios
        .post('/simulation/stop')
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
