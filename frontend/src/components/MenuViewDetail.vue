<template>

    <v-card outlined>
        <v-card-title>
            Menu # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="BookId" v-model="item.bookId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BookName" v-model="item.bookName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Qty" v-model="item.qty" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Price" v-model="item.price" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'MenuViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/menus/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

