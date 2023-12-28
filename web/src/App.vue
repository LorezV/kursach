<script setup lang='ts'>
import Simulation from "./views/simulation/View.vue"
import Products from "./views/product/List.vue"
import Couriers from "./views/courier/List.vue"
import Customers from "./views/customer/List.vue"
import Orders from "./views/order/List.vue"
</script>

<template>
        <div class='flex h-screen flex-col flex-1 overflow-hidden'>
            <header class='sticky top-0 flex justify-between items-center bg-white px-2 py-2 shadow-md'>
                <div>
                    <h1 class='font-medium text-lg'>Симулятор службы доставки еды</h1>
                </div>
                <Simulation />
            </header>

            <main class='flex-1 flex flex-col bg-zinc-100 overflow-x-hidden overflow-y-auto'>
                <div class='flex flex-1 p-4 gap-4'>
                    <div class='flex-1 flex flex-col gap-4'>
                        <Products></Products>
                        <Couriers></Couriers>
                        <Customers></Customers>
                    </div>
                    <div class='flex-1'>
                        <Orders></Orders>
                    </div>
                </div>
            </main>
        </div>
</template>