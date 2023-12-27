<script setup lang='ts'>
import Categories from "../components/category/List.vue"
import Products from "../components/product/List.vue"
</script>
<template>
    <div class='flex flex-1 p-4 gap-4'>
        <div class='w-3/4'>
            <Products></Products>
        </div>
        <div class='w-1/4'>
            <Categories></Categories>
        </div>
    </div>
</template>