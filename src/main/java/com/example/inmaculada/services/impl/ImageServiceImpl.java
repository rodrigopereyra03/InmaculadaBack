package com.example.inmaculada.services.impl;

import com.example.inmaculada.domain.exceptions.ImageNotFoundException;
import com.example.inmaculada.services.IImageService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URI;
import java.util.UUID;

@Service
public class ImageServiceImpl implements IImageService {

    private final MinioClient minioClient;


    @Value("${minio.bucketName}")
    private String bucketName;

    public ImageServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String uploadImage(MultipartFile file) throws Exception{
        String objectName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        }catch (ImageNotFoundException e) {
            // Manejo de errores al obtener el InputStream
            throw new Exception("Error al obtener el InputStream del archivo: " + e.getMessage(), e);
        } catch (MinioException e) {
            // Manejo de errores específicos de Minio
            throw new Exception("Error al cargar el archivo en Minio: " + e.getMessage(), e);
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            throw new Exception("Error inesperado al subir la imagen: " + e.getMessage(), e);
        }
        String url =  minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .method(Method.GET)
                        .build());

        return url;

    }

    private String transformUrl(String originalUrl) {
        try {
            // Parsear la URL original para extraer las partes
            URI originalUri = new URI(originalUrl);

            // Crear una nueva URI con el dominio deseado
            String newHost = "";
            URI transformedUri = new URI(
                    "https",                     // Esquema (https)
                    originalUri.getUserInfo(),   // Información del usuario (normalmente null)
                    newHost,                     // Nuevo host
                    -1,                          // Puerto (usar puerto predeterminado para https)
                    originalUri.getPath(),       // Mantener el path original
                    null,                        // Sin query (si quieres mantenerla, usa originalUri.getQuery())
                    null                         // Sin fragmento
            );

            return transformedUri.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error transformando la URL", e);
        }
    }
}
