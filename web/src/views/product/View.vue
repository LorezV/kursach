<script setup lang='ts'>
import { ref } from "vue"
import { FaRegTrashAlt, FaRegSave } from "vue3-icons/fa";
import type { TItem, TForm } from "../../stores/product"
import useStore from "../../stores/product"

const props = defineProps<{ item: TItem }>()
const emitter = defineEmits<{ (e: 'changed'): void }>()
const store = useStore()
const form = ref<TForm>({
    name: props.item.name,
    weight: props.item.weight,
    price: props.item.price,
    category: props.item.category
})
</script>

<template>
    <tr class='hover:bg-zinc-100'>
        <td class='px-2 w-1/3'>
            <input class='outline-none bg-transparent w-full' v-model='form.name' placeholder='Название' />
        </td>
        <td>
            <input class='outline-none bg-transparent w-full' v-model='form.weight' placeholder='Вес' />
        </td>
        <td>
            <input class='outline-none bg-transparent w-full' v-model='form.price' placeholder='Цена' />
        </td>
        <td class='px-2'>{{ item.productUnits?.length || 0 }}</td>
        <td>
            <input class='outline-none bg-transparent w-full' v-model='form.category' placeholder='Категория' />
        </td>
        <td>
            <div class='flex'>
                <button class='px-2 py-2 text-zinc-500 hover:bg-zinc-200' @click='store.update(item.id, form)'>
                    <FaRegSave></FaRegSave>
                </button>
                <button class='px-2 py-2 text-zinc-500 hover:bg-zinc-200' @click='store.remove(item.id)'>
                    <FaRegTrashAlt></FaRegTrashAlt>
                </button>
            </div>
        </td>
    </tr>
</template>