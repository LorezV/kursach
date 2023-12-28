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
      if (this.item.duration < 15 || this.item.duration > 100) {
        return alert("Период должжен быть не меньше 15 и не больше 100 дней.")
      }

      if (this.item.step < 1 || this.item.step > this.item.duration) {
        return alert("Шаг должжен быть не меньше 1 для и не больше периода.")
      }

      if (this.item.couriers < 3 || this.item.couriers > 9) {
        return alert("Курьеров не может быть меньше 3 и больше 9.")
      }

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
