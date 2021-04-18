// 7. Usuń z bazy osoby o wzroście przekraczającym 190;
printjson(db.runCommand(
	{
	   delete: "people",
	   deletes: [ { q: { height: { $gt: "190" } }, limit: 0 } ]
	}
 ))