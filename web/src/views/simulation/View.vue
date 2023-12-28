<script setup lang='ts'>
import { onMounted } from 'vue';
import useSimulationStore from "../../stores/simulation"

const store = useSimulationStore()

onMounted(() => {
    store.fetchData()

    setInterval(() => {
        if (store.item.status) {
            store.fetchData()
        }
    }, 1000)
})

function click() {
    if (store.item.status) {
        store.stop()
        return
    }

    console.log("asd")

    store.start()
}
</script>

<template>
    <div class='flex flex-1 items-center justify-center bg-zinc-100 relative mx-20' v-if='store.item.status'>
        <div class='absolute left-0 top-0 bottom-0 bg-green-400'
            :style='`right: ${100 - (store.item.steps / store.item.duration * 100)}%;`'></div>
        <div class='z-10'>
            <span>{{ store.item.steps }} / {{ store.item.duration }}</span>
        </div>
    </div>

    <div class='flex'>
        <label class='flex flex-col border-b pl-2'>
            <span class='text-xs'>Курьеры</span>
            <input type="number" v-model='store.item.couriers' min='3' max='15' :disabled='store.item.status'
                class='appearance-none outline-none border-none disabled:bg-zinc-50 disabled:text-zinc-600'>
        </label>

        <label class='flex flex-col border-b'>
            <span class='text-xs'>Шаг</span>
            <input type="number" v-model='store.item.step' min='1' max='3' :disabled='store.item.status'
                class='appearance-none outline-none border-none disabled:bg-zinc-50 disabled:text-zinc-600'>
        </label>

        <label class='flex flex-col border-b border-l pl-2'>
            <span class='text-xs'>Период</span>
            <input type="number" v-model='store.item.duration' min='15' :disabled='store.item.status'
                class='appearance-none outline-none border-none disabled:bg-zinc-50 disabled:text-zinc-600'>
        </label>

        <button class='px-2 py-1 cursor-pointer text-sm text-white font-medium'
            :class='store.item.status ? "bg-red-500" : "bg-green-500"' @click='click'>
            <span v-if='store.item.status'>Остановить</span>
            <span v-else>Запустить</span>
        </button>
    </div>
</template>