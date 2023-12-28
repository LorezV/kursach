<script setup lang='ts'>
import { onMounted } from "vue"
import useStore from "../../stores/courier"
import useSimulationStore from "../../stores/simulation"

const store = useStore()
const storeSimulation = useSimulationStore()

onMounted(() => {
    store.fetchData()
    
    setInterval(() => {
        if (storeSimulation.item.status) {
            store.fetchData()
        }
    }, 1000)
})
</script>

<template>
    <div class='flex flex-col gap-4 shadow-md'>
        <div class='bg-white shadow-md'>
            <div class='p-2 font-medium bg-white'>
                <h2>Курьеры</h2>
            </div>

            <div v-if='store.data && store.data.length' class='flex flex-col'>
                <table class='w-full text-left'>
                    <thead>
                        <tr class='text-xs border-b'>
                            <td class='px-2'>Имя</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for='item in store.data'>
                            <td class='px-2'>{{ item.name }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class='p-2 bg-white' v-else>
                Для того, чтобы курьеры появились, запустите симуляцию.
            </div>
        </div>
    </div>
</template>