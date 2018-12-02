<template>
	<div>
		<Form :model="formItem">
			<FormItem :label="formItem.label_input">
				<Input @keyup.enter.native='input_user' v-model="formItem.input" :placeholder="formItem.content_input" />
			</FormItem>
		</Form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				formItem: {
					label_input: '添加任务',
					input: '',
					content_input: '比如： 复习英语, 提示：按下回车键即可添加任务。'
				}
			}
		},
		methods: {
			input_user() {
				var arr = [{name:"天皇盖地虎",
					detail: this.formItem.input
				}];


				fetch("http://localhost:8888/todo", {
					method: "POST",
					headers: {
						"Content-Type": "application/json",
						"Accept": "application/json"
					},
					body: JSON.stringify(arr)
				}).then(function(resp) {
					resp.json().then((data)=>{
						if(data.data === "添加成功"){
							location.reload();
						}
					})
				});
			}
		}
	}
</script>

<style>

</style>
