package br.com.raimundo.estoque.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ProblemDetail tratarProdutoNaoEncontrado(
            ProdutoNaoEncontradoException ex,
            HttpServletRequest request
    ) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,  //3º a ser mostrado na mensagem
                ex.getMessage() //1 a ser mostrado na mensagem
        );

        problem.setTitle("Produto não encontrado"); //4º a ser mostrado na mensagem
        problem.setInstance(
                URI.create(request.getRequestURI())  //2º a ser mostrado na mensagem
        );
        problem.setProperty(
                "timestamp",
                OffsetDateTime.now() //5º a ser mostrado na mensagem
        );

        return problem;
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ProblemDetail tratarEstoqueInsuficiente(
            EstoqueInsuficienteException ex,
            HttpServletRequest request
    ) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );

        problem.setTitle("Estoque insuficiente");
        problem.setInstance(
                URI.create(request.getRequestURI())
        );
        problem.setProperty(
                "timestamp",
                OffsetDateTime.now()
        );

        return problem;
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ProblemDetail tratarRegraDeNegocio(
            RegraDeNegocioException ex,
            HttpServletRequest request
    ) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );

        problem.setTitle("Regra de negócio violada");
        problem.setInstance(
                URI.create(request.getRequestURI())
        );
        problem.setProperty(
                "timestamp",
                OffsetDateTime.now()
        );

        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail tratarValidacao(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Um ou mais campos possuem valores inválidos"
        );

        problem.setTitle("Dados inválidos");
        problem.setInstance(
                URI.create(request.getRequestURI())
        );
        problem.setProperty(
                "timestamp",
                OffsetDateTime.now()
        );

        Map<String, String> erros =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        erro -> erro.getField(),
                                        erro -> erro.getDefaultMessage(),
                                        (primeiro, segundo) -> primeiro
                                )
                        );

        problem.setProperty("errors", erros);

        return problem;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail tratarJsonInvalido(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "O corpo da requisição está ausente ou possui JSON inválido"
        );

        problem.setTitle("Requisição inválida");
        problem.setInstance(
                URI.create(request.getRequestURI())
        );
        problem.setProperty(
                "timestamp",
                OffsetDateTime.now()
        );

        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail tratarErroInesperado(
            Exception ex,
            HttpServletRequest request
    ) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno inesperado"
        );

        problem.setTitle("Erro interno");
        problem.setInstance(
                URI.create(request.getRequestURI())
        );
        problem.setProperty(
                "timestamp",
                OffsetDateTime.now()
        );

        return problem;
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ProblemDetail tratarParametroInvalido(
            ParametroInvalidoException ex,
            HttpServletRequest request
    ) {

        ProblemDetail problema =
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()
                );

        problema.setTitle("Parâmetro inválido");

        problema.setInstance(
                URI.create(request.getRequestURI())
        );

        problema.setProperty(
                "timestamp",
                OffsetDateTime.now()
        );

        return problema;
    }
}
