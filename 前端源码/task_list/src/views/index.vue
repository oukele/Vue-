<template>
	<div class="layout">
		<Layout>
			<Header style="background: indianred;">
				<h2 style="color: azure;margin: auto;width: 40%;">任 务 计 划 列 表</h2>
			</Header>
			<Content>
				<div style="width: 37%;margin: auto;">
					<Inputs />
					<p style="color:red">{{task_no_number}}项任务未完成</p>
					<Tabs value="name1">
						<TabPane label="所有任务" name="name1">
							<task_list :todo="task_list"></task_list>
						</TabPane>
						<TabPane label="已完成任务" name="name2">
							<task_list :todo="task_yes"></task_list>
						</TabPane>
						<TabPane label="未完成任务" name="name3">
							<task_list :todo="task_no"></task_list>
						</TabPane>
					</Tabs>
				</div>
			</Content>
		</Layout>
	</div>
</template>
<script>
	import Input from '../components/input.vue'
	import task_list from '../components/task_list.vue'
	export default {
		name:'index',
		data() {
			return {
				task_list:[],
				task_yes:[],
				task_no:[],
				task_no_number:0
			}
		},
		created(){
			var that = this;
			fetch("http://localhost:8888/todo", {
				method: "get"
			}).then(function(resp){
				resp.json().then((data) =>{
					let i = 0;
					for (let s of data.data) {
						if( s.state == 1 ){
							that.task_yes.push({id:s.id,content:s.detail,switch_status:true});
							that.task_list.push({id:s.id,content:s.detail,switch_status:true});
						}else{
							that.task_no.push({id:s.id,content:s.detail,switch_status:false});
							that.task_list.push({id:s.id,content:s.detail,switch_status:false});
							i++;
						}
					}
					that.task_no_number = i;
					
				})
			});
		},
		components: {
			Inputs:Input,
			task_list
		}
	}
</script>

<style scoped>

</style>
