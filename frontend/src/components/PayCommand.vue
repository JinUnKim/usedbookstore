<template>
    <v-card outlined>
        <v-card-title>
            Pay
        </v-card-title>

        <v-card-text>
            <Number label="Id" v-model="value.id" :editMode="editMode"/>
            <Number label="OrderId" v-model="value.orderId" :editMode="editMode"/>
            <Number label="BookId" v-model="value.bookId" :editMode="editMode"/>
            <Number label="Price" v-model="value.price" :editMode="editMode"/>
            <String label="PaymentStatus" v-model="value.paymentStatus" :editMode="editMode"/>
            <String label="OrderStatus" v-model="value.orderStatus" :editMode="editMode"/>
            <Number label="Qty" v-model="value.qty" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="pay"
            >
                Pay
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'PayCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.id = 0;
            this.value.orderId = 0;
            this.value.bookId = 0;
            this.value.price = 0;
            this.value.paymentStatus = '';
            this.value.orderStatus = '';
            this.value.qty = 0;
        },
        watch: {
        },
        methods: {
            pay() {
                this.$emit('pay', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

