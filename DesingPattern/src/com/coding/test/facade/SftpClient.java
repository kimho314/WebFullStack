package com.coding.test.facade;

public class SftpClient {
    private Ftp ftp;
    private Reader reader;
    private Writer writer;

    public SftpClient(Ftp ftp, Reader reader, Writer writer) {
        this.ftp = ftp;
        this.reader = reader;
        this.writer = writer;
    }

    public SftpClient(String host, int port, String path, String fileName){
        this.ftp = new Ftp(host, port, path);
        this.reader = new Reader(fileName);
        this.writer = new Writer(fileName);
    }

    public void connect(){
        this.ftp.connect();
        this.reader.fileConnect();
        this.writer.fileConnect();
    }

    public void disConnect(){
        this.writer.fileDisConnect();
        this.reader.fileDisConnect();
        this.ftp.disConnect();
    }

    public void read(){
        this.reader.fileRead();
    }

    public void write(){
        this.writer.write();
    }
}
