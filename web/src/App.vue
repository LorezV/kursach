<script setup lang='ts'>
import { RouterView } from 'vue-router';
import Simulation from "./components/Simulation.vue"
</script>

<template>
    <div class='flex flex-row h-screen overflow-hidden'>
        <aside class='flex flex-col'>
            <nav>
                <ul>
                    <li>
                        <a href=""></a>
                    </li>
                </ul>
            </nav>
        </aside>

        <div class='flex flex-col flex-1 overflow-x-hidden overflow-y-auto'>
            <header class='sticky top-0 flex justify-between items-center bg-white px-2 py-2'>
                <div>
                    <h1 class='font-medium text-lg'>Симулятор службы доставки еды</h1>
                </div>
                <Simulation />
            </header>

            <main class='flex-1 flex flex-col bg-zinc-100'>
                <RouterView></RouterView>
            </main>
        </div>
    </div>
</template>