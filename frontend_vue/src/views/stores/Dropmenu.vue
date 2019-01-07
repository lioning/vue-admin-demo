<template>
  <div class="share-dropdown-menu">
      <span class="share-dropdown-menu-title" @click.self="clickTitle">{{title}}</span>
      <div class="share-dropdown-menu-item" :style="animate(index+1)" v-for="(item,index) of arrays" :key='index'>
        <span >{{item.name}}</span>
      </div>
  </div>
</template>

<script>
export default {
  name: 'dropdownMenu',
  props: {
    title: String,
    arrays: {
      type: Array,
      default: () => ([])
    }
  },
  data() {
    return {
      isActive: false
    }
  },
  methods: {
    clickTitle() {
      this.isActive = !this.isActive
    },
    animate(index) {
      if (!this.isActive) {
        return `z-index: -1;
         transition-delay: ${index * 0.1}s;
         transform: translate3d(0, 0, 0);`
      }
      return `z-index: 1;
          transition-delay: ${(this.arrays.length - index) * 0.1}s;
          transform: translate3d(0, ${index * 60}px, 0);`
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.share-dropdown-menu {
  width: 100%;
  position: relative;
  z-index: 1;
  &-title {
    position: absolute;
    width: 100%;
    display: block;
    cursor: pointer;
    background: black;
    color: white;
    height: 60px;
    line-height: 60px;
    font-size: 20px;
    text-align: center;
    z-index: 3;
    transform: translate3d(0, 0, 0);
  }
  &-item {
    text-align: center;
    position: absolute;
    width: 100%;
    background: #e0e0e0;
    line-height: 60px;
    height: 60px;
    cursor: pointer;
    font-size: 20px;
    opacity: 1;
    transition: transform 0.28s ease;
    &:hover {
      background: black;
      color: white;
    }
  }
}
</style>