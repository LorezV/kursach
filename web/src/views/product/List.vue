<script setup lang='ts'>
import { ref, onMounted } from "vue"
import View from './View.vue';
import { FaPlus } from "vue3-icons/fa";
import type { TForm } from '../../stores/product';
import useStore from "../../stores/product"
import useSimulationStore from "../../stores/simulation"

const store = useStore()
const storeSimulation = useSimulationStore()
const form = ref<TForm>({
    name: "",
    weight: 0,
    price: 0,
    category: ""
})

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
                <h2>Создание продукта</h2>
            </div>

            <div class='flex'>
                <label class='flex flex-col flex-1'>
                    <span class='text-xs px-2'>Название</span>
                    <input type="text" v-model='form.name' placeholder='Название' class='outline-none px-2 py-1 border-b'>
                </label>
                <label class='flex flex-col flex-1'>
                    <span class='text-xs px-2'>Вес</span>
                    <input type="number" v-model='form.weight' placeholder='Вес'
                        class='outline-none px-2 py-1 border-b border-l'>
                </label>
                <label class='flex flex-col flex-1'>
                    <span class='text-xs px-2'>Цена</span>
                    <input type="number" v-model='form.price' placeholder='Цена'
                        class='outline-none px-2 py-1 border-b border-l'>
                </label>
                <label class='flex flex-col flex-1'>
                    <span class='text-xs px-2'>Категория</span>
                    <input type="text" v-model='form.category' placeholder='Категория'
                        class='outline-none px-2 py-1 border-b border-l'>
                </label>
                <button class='text-green-500 border-b border-l hover:bg-green-100 px-2 py-2'
                    @click='() => { console.log(form); store.create(form) }'>
                    <FaPlus></FaPlus>
                </button>
            </div>
        </div>

        <div class='bg-white shadow-md'>
            <div class='p-2 font-medium bg-white'>
                <h2>Продукты</h2>
            </div>

            <div v-if='store.data && store.data.length' class='flex flex-col'>
                <table class='w-full text-left'>
                    <thead>
                        <tr class='text-xs border-b'>
                            <td class='pb-2 px-2'>Название</td>
                            <td class='pb-2'>Вес</td>
                            <td class='pb-2'>Цена</td>
                            <td class='pb-2'>Кол-во</td>
                            <td class='pb-2'>Категория</td>
                            <td class='pb-2'></td>
                        </tr>
                    </thead>
                    <tbody>
                        <View :item='item' v-for='item in store.data'></View>
                    </tbody>
                </table>
            </div>
            <div class='p-2 bg-white' v-else>
                Не существует ни одного продукта.
            </div>
        </div>
    </div>
</template>