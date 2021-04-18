// Dodaj siebie do bazy, zgodnie z formatem danych użytych dla innych osób 
// (dane dotyczące karty kredytowej, adresu zamieszkania i wagi mogą być fikcyjne);
printjson(db.people.insertOne({
	"sex" : "Male",
	"first_name" : "Karol",
	"last_name" : "Gzik",
	"job" : "Software Engineer",
	"email" : "bzik@intel.com",
	"location" : {
		"city" : "Gdansk",
		"address" : {
			"streetname" : "Dluga",
			"streetnumber" : "15"
		}
	},
	"description" : "jakis cudak co udaje ze potrafi programowac",
	"height" : "183",
	"weight" : "666",
	"birth_date" : "1997-01-17T00:00:00Z",
	"nationality" : "Polish",
	"credit" : [
		{
			"type" : "mastercard",
			"number" : "56022541652043117",
			"currency" : "COP",
			"balance" : "3843.63"
		},
		{
			"type" : "jcb",
			"number" : "5602220902466171",
			"currency" : "SEK",
			"balance" : "3870.48"
		}
	]
}))