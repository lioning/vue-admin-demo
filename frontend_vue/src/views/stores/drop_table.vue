<template>

  <div class="dropdown-menu">
    <dropdown-menu  :title="title" :arrays="arrays"></dropdown-menu>
  </div>

</template>

<script>
//import DropdownMenu from './dropdownMenu.vue'
import { DropdownMenu} from '../../routes';
export default {
  components: {
    DropdownMenu
  },
  data() {
    return {
      title: '排好队',
      arrays: [
        { name: '1排好队，一个一个来' },
        { name: '2排好队，一个一个来' },
        { name: '3排好队，一个一个来' },
        { name: '4排好队，一个一个来' },
        { name: '5排好队，一个一个来' },
        { name: '6排好队，一个一个来' },
        { name: '7排好队，一个一个来' },
        { name: '8排好队，一个一个来' },
        { name: '9排好队，一个一个来' },
        { name: '10排好队，一个一个来' }
      ]
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dropdown-menu{
  width: 250px;
}

</style>