<template>
	<div>
		<div v-for="item in todo" :key="item.id">
			<div style="background:wheat;padding: 20px;border-bottom:2px solid aliceblue;">
				<p>
					<i-switch size="large" @on-change="change($event,item.id)" v-model=" item.switch_status ">
						<span slot="open">完成</span>
						<span slot="close">取消</span>
					</i-switch>
					<span style="margin-left: 10px;">{{item.content}}</span>
					<span @click="delMsg(item.id)" class="del">
						<Icon type="ios-trash" />
					</span>
				</p>
			</div>
			<Modal v-model="modal2" width="360">
				<p slot="header" style="color:#f60;text-align:center">
					<Icon type="ios-information-circle"></Icon>
					<span>确定删除吗？</span>
				</p>
				<div style="text-align:center">
					<p>删除后不可恢复哦。</p>
					<p>你确定要删除吗？</p>
				</div>
				<div slot="footer">
					<Button type="error" size="large" long :loading="modal_loading" @click="del">Delete</Button>
				</div>
			</Modal>
		</div>

	</div>
</template>

<script>
	export default {
		props: ["todo"],
		data() {
			return {
				del_id: "",
				modal2: false,
				modal_loading: false
			}
		},
		methods: {
			delMsg(e) {
				this.modal2 = true;
				this.del_id = e;
			},
			del() {

				fetch("http://localhost:8888/todo/del/"+this.del_id+"", {
					method: "get"
				}).then(function(resp) {
					resp.json().then((data) => {
						if( data.data === "删除成功" ){
							location.reload();
						}
					})
				});
			},
			change(status, id) {
				let number = 0;
				if (status) {
					number = 1;
				} else {
					number = 0;
				}

				fetch("http://localhost:8888/todo/status/" + id + "/" + number + "", {
					method: "get"
				}).then(function(resp) {
					resp.json().then((data) => {
						console.log(data);
						if (data.data === "修改成功") {
							location.reload();
						}
					});
				});

			}
		}
	}
</script>

<style>
	.del {
		float: right;
		font-size: 18px;
		color: red;
	}

	.del:hover {
		color: chartreuse;
	}
</style>
