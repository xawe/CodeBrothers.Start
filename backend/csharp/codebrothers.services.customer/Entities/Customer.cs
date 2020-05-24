using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace codebrothers.services.customer.Entities
{
    [Table("tb_customer")]
    public class Customer
    {
        [Column("id")]
        [Key]
        public long id { get; set; }
        [StringLength(50, MinimumLength = 3), Required]
        [Column("nome")]
        public string nome { get; set; }
        [Column("sobrenome")]
        public string sobrenome { get; set; }
        [Column("rg")]
        public string rg { get; set; }
        [RegularExpression(@"([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})"), Required, StringLength(14)]
        [Column("cpf")]
        public string cpf { get; set; }
        [Column("ativo")]
        public bool ativo { get; set; }
        [Column("criadoem")]
        public DateTime criadoEm { get; set; }
        [Column("atualizadoem")]
        public DateTime? atualizadoEm { get; set; }
        [Column("email")]
        public string email { get; set; }
    }
}