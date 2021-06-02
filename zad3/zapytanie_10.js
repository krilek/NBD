// 9.	Dodaj do wszystkich osób o imieniu Antonio własność „hobby” o wartości „pingpong”; 
printjson(db.people.updateMany(
	{
		"job": "Editor"
	},
	{
		$unset: {
			"email": true
		}
	},
	{ multi: true }
)
)