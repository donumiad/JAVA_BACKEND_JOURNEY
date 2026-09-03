package br.com.raimundo.estoque.repository;

import br.com.raimundo.estoque.model.Produto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProdutoRepositoryJdbc implements ProdutoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProdutoRowMapper rowMapper = new ProdutoRowMapper();

    private static final String COLUNAS =
            "id, nome, preco, estoque";

    private static final Map<String, String> COLUNAS_ORDENACAO =
            Map.of(
                    "id", "id",
                    "nome", "nome",
                    "preco", "preco",
                    "estoque", "estoque"
            );

    public ProdutoRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Produto> listarTodos() {
        String sql = "SELECT " + COLUNAS + " FROM produtos ORDER BY nome ";

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        String sql = """
                SELECT id, nome, preco, estoque
                FROM produtos
                WHERE id = ?
                """;

        try {
            Produto produto = jdbcTemplate.queryForObject(
                    sql,
                    rowMapper,
                    id
            );

            return Optional.of(produto);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        String sql = """
                SELECT id, nome, preco, estoque
                FROM produtos
                WHERE nome ILIKE ?
                ORDER BY nome
                """;

        return jdbcTemplate.query(
                sql,
                rowMapper,
                "%" + nome + "%"
        );
    }

    @Override
    public Produto salvar(Produto produto) {
        String sql = """
            INSERT INTO produtos (nome, preco, estoque)
            VALUES (?, ?, ?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement statement =
                    connection.prepareStatement(
                            sql,
                            new String[]{"id"}
                    );

            statement.setString(1, produto.getNome());
            statement.setBigDecimal(2, produto.getPreco());
            statement.setInt(3, produto.getEstoque());

            return statement;

        }, keyHolder);

        Number idGerado = keyHolder.getKey();

        return new Produto(
                idGerado.longValue(),
                produto.getNome(),
                produto.getPreco(),
                produto.getEstoque()
        );
    }

    @Override
    public boolean atualizar(Produto produto) {
        String sql = """
                UPDATE produtos
                SET nome = ?, preco = ?
                WHERE id = ?""";

        int linhasAfetas = jdbcTemplate.update(
                sql, produto.getNome(),
                produto.getPreco(),
                produto.getId()
        );
        return  linhasAfetas > 0;
    }

    @Override
    public boolean removerPorId(Long id) {
        String sql = """
            DELETE FROM produtos
            WHERE id = ?
            """;

        int linhasAfetadas =
                jdbcTemplate.update(sql, id);

        return linhasAfetadas > 0;
    }

    @Override
    public boolean atualizarEstoque(Long id, Integer novoEstoque) {
        String sql = """
            UPDATE produtos
            SET estoque = ?
            WHERE id = ?
            """;

        int linhasAfetadas = jdbcTemplate.update(
                sql,
                novoEstoque,
                id
        );

        return linhasAfetadas > 0;
    }

    @Override
    public List<Produto> buscarPaginado(
            String nome,
            int page,
            int size,
            String sort,
            String direction
    ) {

        String colunaOrdenacao =
                COLUNAS_ORDENACAO.get(sort);
        if (colunaOrdenacao == null) {
            throw new IllegalArgumentException(
                    "Ordenação não suportada pelo repository: " + sort
            );
        }

        String direcao =
                direction.equalsIgnoreCase("desc")
                        ? "DESC"
                        : "ASC";

        StringBuilder sql = new StringBuilder(""" 
            SELECT id, nome, preco, estoque
            FROM produtos
            WHERE 1 = 1
            """);  //entender o que é esse stringbuilder

        List<Object> parametros = new ArrayList<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND nome ILIKE ?");
            parametros.add("%" + nome + "%");
        }

        sql.append(" ORDER BY ")
                .append(colunaOrdenacao)
                .append(" ")
                .append(direcao);

        sql.append(" LIMIT ? OFFSET ?");

        parametros.add(size);
        parametros.add(page * size);

        return jdbcTemplate.query(
                sql.toString(),
                rowMapper, //Transforma cada linha do resultSet em Produto
                parametros.toArray() //O JDBCTEMPLATE recebe 3 parametros
                                    //o SQL, o tipo de retorno e os parametros que iram subistituir os "?"
        );
    }

    @Override
    public long contar(String nome) {

        StringBuilder sql = new StringBuilder("""
            SELECT COUNT(*)
            FROM produtos
            WHERE 1 = 1
            """);

        List<Object> parametros = new ArrayList<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND nome ILIKE ?");
            parametros.add("%" + nome + "%");
        }

        Long total = jdbcTemplate.queryForObject(
                sql.toString(),
                Long.class,
                parametros.toArray()
        );

        return total != null ? total : 0L;
    }

}
