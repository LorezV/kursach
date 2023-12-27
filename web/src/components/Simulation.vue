<script setup lang='ts'>
import { onBeforeMount } from 'vue';
import useSimulationStore from "../stores/simulation"

const simulation = useSimulationStore()

onBeforeMount(simulation.fetchData)

function click() {
    if (simulation.running) {
        simulation.stop()
        return
    }

    simulation.start()
}

</script>

<template>
    <div class='flex'>
        <label class='flex flex-col border-b border-l pl-2'>
            <span class='text-xs'>Кол-во курьеров</span>
            <input type="number" v-model='simulation.form.duration' min='15'
                class='appearance-none outline-none border-none'>
        </label>

        <label class='flex flex-col border-b'>
            <span class='text-xs'>Шаг</span>
            <input type="number" v-model='simulation.form.step' min='1' max='3'
                class='appearance-none outline-none border-none'>
        </label>

        <label class='flex flex-col border-b border-l pl-2'>
            <span class='text-xs'>Период</span>
            <input type="number" v-model='simulation.form.duration' min='15'
                class='appearance-none outline-none border-none'>
        </label>

        <button class='px-2 py-1 cursor-pointer text-sm text-white font-medium'
            :class='simulation.running ? "bg-red-500" : "bg-green-500"' @click='click'>
            <span v-if='simulation.running'>Остановить</span>
            <span v-else>Запустить</span>
        </button>
</div></template>