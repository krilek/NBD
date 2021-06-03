// 5. Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty
printjson(
  db.people.aggregate([
    {
      $match: { nationality: 'Poland', sex: 'Female' }
    },
    {
      $unwind: {
        path: '$credit'
      }
    },
    {
      $group: {
        _id: '$credit.currency',
        total: {
          $sum: { "$convert": { 'input': '$credit.balance', 'to': 'double' } }
        },
        avarage: {
          $avg: { "$convert": { 'input': '$credit.balance', 'to': 'double' } }
        }
      }
    }
  ]).toArray()
);