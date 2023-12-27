<script setup lang='ts'>
import { ref } from "vue"
import { FaRegTrashAlt, FaRegSave } from "vue3-icons/fa";
import type { TItem, TForm } from "../../stores/category"
import useStore from "../../stores/category"

const props = defineProps<{ item: TItem }>()
const emitter = defineEmits<{ (e: 'changed'): void }>()
const store = useStore()
const form = ref<TForm>({
    name: props.item.name
})
</script>

<template>
        <tr class='hover:bg-zinc-100'>
        <td class='px-2'>{{ item.id }}</td>
        <td class='w-full'>
            <input class='outline-none bg-transparent w-full' v-model='form.name' />
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