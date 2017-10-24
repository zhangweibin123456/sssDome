$package('XHY.login');
XHY.login = function() {
	return {
		toLogin : function() {
			try {
				var form = $("#loginForm");
				if (form.form('validate')) {
					XHY.progress('Please waiting', 'Loading...');
					XHY.submitForm(form, function(data) {
						XHY.closeProgress();
						if (data != null) {
							if (data.status == "000") {
								window.location = "/sssDome/view/main/main.jsp";
							} else {
								XHY.alert('提示', data.message, 'error',
										function() {
											$(this).dialog('close');
										});
							}
						}
					}

					);

				}
			} catch (e) {

			}
			return false;
		},
		init : function() {
			if (window.top != window.self) {
				window.top.location = window.self.location;
			}

			var loginForm = $("#loginForm");
			
			$("#btnLogin").bind('click', function() {
				$("#btnLogin").unbind();
				loginForm.submit(XHY.login.toLogin);
			});

		}
	}
}();

$(function() {
	XHY.login.init();
});