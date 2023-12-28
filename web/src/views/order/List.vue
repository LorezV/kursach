<script setup lang='ts'>
import { onMounted } from "vue"
import useStore from "../../stores/order"
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
    <div class='flex flex-col flex-1 gap-4 shadow-md'>
        <div class='bg-white shadow-md'>
            <div class='p-2 bg-white flex justify-between'>
                <h2 class='font-medium '>Заказы</h2>
                <div class='flex gap-4'>
                    <span>Всего: {{ store.data.length }}</span>
                    <span>Доставляются: {{ store.data.filter(e => !!e.courier && !e.ready).length }}</span>
                    <span>Выполненных: {{ store.data.filter(e => e.ready).length }}</span>
                </div>
            </div>

            <div v-if='store.data && store.data.length' class='flex flex-col gap-8 p-4 shadow-inner'>
                <div v-for='item in store.data' class='flex flex-col gap-2'>
                    <div class='flex items-center justify-between px-4 py-2 bg-gradient-to-br rounded-t-md'
                        :class='item.ready ? "from-green-400 to-green-500" : item.courier ? "from-orange-400 to-orange-500" : "from-blue-400 to-blue-500"'>
                        <div class='text-lg text-white font-semibold'>Заказ #{{ item.id }}</div>
                        <div class='text-white'>{{ item.ready ? "Выполнен" : item.courier ? "Доставка" : "Новый" }}</div>
                    </div>
                    <div class='flex flex-col gap-2 px-4 py-2 bg-zinc-100'>
                        <div class='flex justify-between' v-for='orderProduct in item.orderProducts'>
                            <div>{{ orderProduct.product.name }}</div>
                            <div>{{ orderProduct.quantity }} шт.</div>
                        </div>
                    </div>
                    <div class='flex flex-wrap gap-4 px-4 py-2 bg-zinc-100'>
                        <span><strong>Клиент: </strong>{{ item.customer.name }}</span>
                        <span><strong>Адрес: </strong>{{ item.customer.address }}</span>
                        <span><strong>Телефон: </strong>{{ item.customer.phone }}</span>
                    </div>
                    <div class='flex flex-wrap justify-between rounded-b gap-4 px-4 py-2 bg-zinc-800 text-white'>
                        <span><strong>Сумма: </strong>{{ item.price }} ₽</span>
                        <span v-if='item.courier'><strong>Курьер: </strong>{{ item.courier.name }}</span>
                    </div>
                </div>
            </div>

            <div class='p-2 bg-white' v-else>
                Для того, чтобы заказы появились, запустите симуляцию.
            </div>
        </div>
    </div>
</template>