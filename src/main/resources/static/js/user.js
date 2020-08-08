 let index = {
	init:function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
	},
	
	save:function(){
		//alert('user의 save 함수 호출됨aa');
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		}
		console.log(data);
		
		$.ajax().done().fail();//통신을 통해서 3개의 파라메터를 json으로 변경하여 인서트요청.
	
	}
	
}

index.init();