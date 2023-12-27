<script setup lang='ts'>
import { ref, onBeforeMount } from "vue"
import type { TForm } from '../../stores/category';
import View from './View.vue';
import { FaPlus } from "vue3-icons/fa";
import useStore from "../../stores/category"

const store = useStore()

onBeforeMount(store.fetchData)

const form = ref<TForm>({
    name: ""
})
</script>

<template>
    <div class='flex flex-col gap-4 shadow-md max-h-[25rem]'>
        <div class='bg-white shadow-md'>
            <div class='p-2 font-medium bg-white'>
                <h2>Создание категории</h2>
            </div>

            <div class='flex'>
                <label class='flex flex-col flex-1'>
                    <span class='text-xs px-2'>Название</span>
                    <input type="text" v-model='form.name' placeholder='Название' class='outline-none px-2 py-1 border-b'>
                </label>
                <button class='text-green-500 border-b border-l hover:bg-green-100 px-2 py-2'
                    @click='() => { console.log(form); store.create(form) }'>
                    <FaPlus></FaPlus>
                </button>
            </div>
        </div>

        <div class='bg-white shadow-md'>
            <div class='p-2 font-medium bg-white'>
                <h2>Категории</h2>
            </div>

            <div v-if='store.data && store.data.length' class='flex flex-col overflow-auto'>
                <table class='w-full text-left'>
                    <thead>
                        <tr class='text-xs border-b'>
                            <td class='px-2 pb-2'>#</td>
                            <td class='pb-2'>Название</td>
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