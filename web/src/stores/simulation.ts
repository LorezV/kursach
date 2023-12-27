import axios from '../axios'
import { defineStore } from 'pinia'

export default defineStore('simulation', {
  state: () => {
    return {
      form: {
        duration: 10,
        step: 1,
        couriers: 5,
      },
      running: false,
      timeout: -1,
    }
  },
  actions: {
    fetchData() {
      axios
        .get<{ status: boolean }>('simulation/status')
        .then((response) => {
          if (response.status == 200) {
            this.running = response.data.status
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },

    start() {
      axios
        .post('/simulation/start', {
          duration: this.form.duration,
          step: this.form.step,
        })
        .then((response) => {
          if (response.status == 200) {
            this.fetchData()
            setTimeout(this.fetchData, this.form.duration * 1000)
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

            if (this.timeout > -1) {
              clearTimeout(this.timeout)
              this.timeout = -1
            }
          }
        })
        .catch((e: unknown) => {
          console.error(e)
        })
    },
  },
})
