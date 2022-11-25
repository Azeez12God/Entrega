package com.alberto.ut1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Scanner;

/**
 * Objetos futbolista (id,nombre,mediaGoles) con opciones de alta y consulta en un fichero RAF
 *
 */
public class RAFutbol
{
    private static final int size = 50;

    public static void escribir(RandomAccessFile raf){
        try{
            Scanner ent = new Scanner(System.in);
            System.out.println("Introduce la id del futbolista:");
            int id = ent.nextInt();
            ent.nextLine();
            System.out.println("Introduce el nombre del futbolista:");
            String nom = ent.nextLine();
            StringBuffer sb = new StringBuffer(nom);
            sb.setLength(15);
            System.out.println("Introduce la media de goles:");
            double media = ent.nextDouble();
            Futbolista f = new Futbolista(id,nom,media);
            raf.seek((long)(f.getIdJug() -1)*size);
            raf.writeInt(f.getIdJug());
            raf.writeUTF(f.getNombre());
            raf.writeDouble(f.getMediaGoles());
        }
        catch(IOException e1){
            e1.printStackTrace();
        }
    }

    public static void leer(RandomAccessFile raf, int x){
        try{
            String nombre = null; double media = 0;
            raf.seek((long)(x-1)*size);
            int id  = raf.readInt();

            if(x==id) {
                nombre = raf.readUTF();
                media = raf.readDouble();
                System.out.println("ID:" + id + "\nNombre:" + nombre + "\nSalario:" + media);
            }

            else
                System.out.println("No se ha encontrado al futbolista con esa ID.");

        }
        catch(EOFException i){
        }
        catch(IOException e2){
            e2.printStackTrace();
        }
    }

    public static void listar(RandomAccessFile raf){
        try {
            raf.seek(0);
            while (true) {
                int id = raf.readInt();
                if(id==0)
                    break;
                String nombre = raf.readUTF();
                double media = raf.readDouble();
                System.out.println(id + ".  Nombre:" + nombre + "  Media goles:" + media);
                System.out.println();
            }
        }

        catch (EOFException eo){}
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copiar(RandomAccessFile raf){
        try{
            FileChannel fcLeer = raf.getChannel();
            Path pegado = Paths.get("pegar.dat");
            FileChannel fcEsc = FileChannel.open(pegado,StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            ByteBuffer bb = ByteBuffer.allocate(size);

            while(fcLeer.read(bb)>0){
                bb.flip();
                fcEsc.write(bb);
                bb.rewind();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try(RandomAccessFile raf = new RandomAccessFile("futbol.dat", "rw")) {
            raf.setLength(5000);
            Scanner ent = new Scanner(System.in);
            int opc = 1;
            while(opc!=0) {
                System.out.println("Menú de futbolista:\n1. Alta de jugador\n2. Lectura de jugador\n3. Copiar fichero\n4. Listar componentes del fichero\n0. Salir\n");
                opc = ent.nextInt();
                switch (opc) {
                    case 0:
                        System.exit(0);

                    case 1:
                        escribir(raf);
                        System.out.println("Agregado con éxito.");
                        System.out.println();
                        break;

                    case 2:
                        System.out.println("Introduce la id que quieres leer:");
                        int id = ent.nextInt();
                        leer(raf, id);
                        System.out.println();
                        break;

                    case 3:
                        copiar(raf);
                        System.out.println("Copiado con éxito");
                        break;

                    case 4:
                        listar(raf);
                        break;
                }
            }
        }
        catch(IOException i){
            System.err.println(i.getMessage());
        }
    }
}
