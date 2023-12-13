package Controlador;

import Modelo.ModeloCliente;
import Modelo.ModeloFactura_Compra;
import Modelo.ModeloProducto;
import Modelo.ModeloProveedor;
import Modelo.ModeloUsuario;
import Modelo.ModeloVenta;
import Vista.Cliente;
import Vista.Mostra_Detalle_Factura_Compra;
import Vista.Factura;
import Vista.Factura_Compra;
import Vista.Login;
import Vista.Principal;
import Vista.Producto;
import Vista.Agregar_Detalle_Factura;
import Vista.Buscar_producto;
import Vista.Proveedor;
import Vista.Usuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class ControladorPrincipal implements ActionListener, ChangeListener, DocumentListener {

    Login log = new Login();
    Principal prin = new Principal();
    ModeloUsuario modusu = new ModeloUsuario();
    ControladorUsuario usu = new ControladorUsuario();
    Usuario user = new Usuario();
    Cliente cli = new Cliente();
    ControladorCliente clic = new ControladorCliente();
    Proveedor pro = new Proveedor();
    ControladorProveedor proc = new ControladorProveedor();
    Producto produc = new Producto();
    Factura factu = new Factura();
    Factura_Compra factucom = new Factura_Compra();
    ControladorProducto conpro = new ControladorProducto();
    ModeloCliente modcli = new ModeloCliente();
    ModeloProveedor modpro = new ModeloProveedor();
    ModeloProducto modproduc = new ModeloProducto();
    ControladorFactura_Compra controlfaccom = new ControladorFactura_Compra();
    ModeloFactura_Compra modfactucom = new ModeloFactura_Compra();
    ModeloVenta modventa = new ModeloVenta();
    ControladorVenta conventa = new ControladorVenta();
    Agregar_Detalle_Factura detallefactu = new Agregar_Detalle_Factura();
    Mostra_Detalle_Factura_Compra detallefactucompra = new Mostra_Detalle_Factura_Compra();
    Buscar_producto tableproducto = new Buscar_producto();

    public ControladorPrincipal() {
        prin.getBtnNuevoUsuario().addActionListener(this);
        user.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prin.getBtnNuevoCliente().addActionListener(this);
        cli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prin.getBtnAñadirProveedor().addActionListener(this);
        pro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prin.getJPPrincipal().addChangeListener(this);
        prin.getBtnNuevoUsuario().addChangeListener(this);

        prin.getBtnNuevoProducto().addActionListener(this);
        produc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prin.getBtnNuevaFactura().addActionListener(this);
        factucom.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prin.getBtnNuevaVenta().addActionListener(this);
        factu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        detallefactu.getBtnBuscarProducto().addActionListener(this);
        detallefactu.getBtnagregardetallefactura().addActionListener(this);
        detallefactu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detallefactucompra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableproducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prin.getTxtFiltroVenta().getDocument().addDocumentListener(this);
        prin.getTxtFiltro().getDocument().addDocumentListener(this);
        prin.getTxtFiltroCliente().getDocument().addDocumentListener(this);
        prin.getTxtFiltroProvedor().getDocument().addDocumentListener(this);
        prin.getTxtFiltroProducto().getDocument().addDocumentListener(this);
        prin.getTxtFiltroFactura().getDocument().addDocumentListener(this);
        tableproducto.getTxtFiltro().getDocument().addDocumentListener(this);
    }

    public void iniciarPrincipal(int valor) {
        log.setVisible(false);
        prin.setLocationRelativeTo(null);
        prin.setVisible(true);
        prin.setTitle("Panel Principal | Ventana");
        prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gestionarUsuario();
        prin.getJPPrincipal().setSelectedIndex(valor);

    }

    public void gestionarUsuario() {

        modusu.mostrarTablaUsuario(prin.getJtUsuario(), "", "Usuario");

        prin.getTxtFiltro().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getTxtFiltro().setText("");
                prin.getTxtFiltro().setForeground(Color.black);
            }

        });

        prin.getJtUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getJtUsuario().rowAtPoint(e.getPoint());
                int columna = prin.getJtUsuario().columnAtPoint(e.getPoint());
                modusu.setDoc(Integer.parseInt(prin.getJtUsuario().getValueAt(fila, 0).toString()));

                if (columna == 9) {
                    prin.setVisible(false);
                    usu.actualizarUsuario(modusu.getDoc());
                }

                if (columna == 10) {

                    usu.eliminarUsuario(modusu.getDoc());
                    modusu.mostrarTablaUsuario(prin.getJtUsuario(), "", "Usuario");
                }

            }

        });
    }

    public void gestionarFactura_Compra() {

        detallefactu.addWindowListener(new WindowAdapter() {
            ;
        public void windowClosed(WindowEvent e) {
                detallefactu.setVisible(false);
                iniciarPrincipal(4);
                gestionarFactura_Compra();
            }
        });

        detallefactucompra.addWindowListener(new WindowAdapter() {
            ;
        public void windowClosed(WindowEvent e) {
                detallefactucompra.setVisible(false);
                iniciarPrincipal(4);
            }
        });

        modfactucom.mostrarFactura_Compra(prin.getJtFactura(), "", "Factura");

        prin.getTxtFiltroFactura().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getTxtFiltroFactura().setText("");
                prin.getTxtFiltroFactura().setForeground(Color.black);
            }

        });

        prin.getJtFactura().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getJtFactura().rowAtPoint(e.getPoint());
                int columna = prin.getJtFactura().columnAtPoint(e.getPoint());
                modfactucom.setIdfactu(Integer.parseInt(prin.getJtFactura().getValueAt(fila, 0).toString()));
                if (columna == 11) {
                    prin.setVisible(false);
                    controlfaccom.actualizarFactura_Compra(modfactucom.getIdfactu());
                }

                if (columna == 10) {
                    prin.setVisible(false);
                    detallefactu.setVisible(true);
                    detallefactu.setLocationRelativeTo(null);
                    detallefactu.setTitle("Agregar Detalle Factura | Ventana");
                    modfactucom.mostrarDetalleFactura(detallefactu.getJtDetalleFactura(), "", "");

                }

                if (columna == 9) {
                    prin.setVisible(false);
                    detallefactucompra.setVisible(true);
                    detallefactucompra.setLocationRelativeTo(null);
                    detallefactucompra.setTitle("Mostrar Detalle Factura | Ventana");
                    modfactucom.mostrarDetalleFactura(detallefactucompra.getJtDetalleFactura(), "", "MostrarFacturaCompra");
                }

            }

        });
    }

    public void gestionarCliente() {

        modcli.mostrarTablaCliente(prin.getJtCliente(), "", "Cliente");

        prin.getTxtFiltroCliente().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getTxtFiltroCliente().setText("");
                prin.getTxtFiltroCliente().setForeground(Color.black);
            }

        });

        prin.getJtCliente().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getJtCliente().rowAtPoint(e.getPoint());
                int columna = prin.getJtCliente().columnAtPoint(e.getPoint());
                modcli.setCdl(Integer.parseInt(prin.getJtCliente().getValueAt(fila, 0).toString()));
                if (columna == 8) {
                    prin.setVisible(false);
                    clic.actualizarCliente(modcli.getCdl());
                }

                if (columna == 9) {

                    clic.eliminarCliente(modcli.getCdl());
                    modcli.mostrarTablaCliente(prin.getJtCliente(), "", "Cliente");
                }

            }

        });
    }

    public void gestionarProducto() {

        modproduc.mostrarTablaProducto(prin.getJtProducto(), "", "Producto");

        prin.getTxtFiltroProducto().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getTxtFiltroProducto().setText("");
                prin.getTxtFiltroProducto().setForeground(Color.black);
            }

        });

        prin.getJtProducto().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getJtProducto().rowAtPoint(e.getPoint());
                int columna = prin.getJtProducto().columnAtPoint(e.getPoint());
                modproduc.setIdpro(Integer.parseInt(prin.getJtProducto().getValueAt(fila, 0).toString()));
                if (columna == 6) {
                    prin.setVisible(false);
                    conpro.actualizarProducto(modproduc.getIdpro());
                }

                if (columna == 7) {

                    conpro.eliminarProducto(modproduc.getIdpro());
                    modproduc.mostrarTablaProducto(prin.getJtProducto(), "", "Producto");
                }

            }

        });
    }

    public void gestionarProveedor() {

        modpro.mostrarTablaProvedor(prin.getJtProvedor(), "", "Provedor");

        prin.getTxtFiltroProvedor().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getTxtFiltroProvedor().setText("");
                prin.getTxtFiltroProvedor().setForeground(Color.black);
            }

        });

        prin.getJtProvedor().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getJtProvedor().rowAtPoint(e.getPoint());
                int columna = prin.getJtProvedor().columnAtPoint(e.getPoint());
                modpro.setCed(Integer.parseInt(prin.getJtProvedor().getValueAt(fila, 0).toString()));
                if (columna == 9) {
                    prin.setVisible(false);
                    proc.actualizarProvedor(modpro.getCed());
                }

                if (columna == 10) {

                    proc.eliminarProvedor(modpro.getCed());
                    modpro.mostrarTablaProvedor(prin.getJtProvedor(), "", "Provedor");
                }

            }

        });
    }

    public void gestionarVenta() {

        modventa.mostrarVentaTabla(prin.getJtVenta(), "", "Editar");
        prin.getTxtFiltroVenta().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getTxtFiltroVenta().setText("");
                prin.getTxtFiltroVenta().setForeground(Color.black);
            }

        });

        prin.getJtVenta().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getJtVenta().rowAtPoint(e.getPoint());
                int columna = prin.getJtVenta().columnAtPoint(e.getPoint());
                modpro.setCed(Integer.parseInt(prin.getJtVenta().getValueAt(fila, 0).toString()));
                if (columna == 8) {
                    prin.setVisible(false);
                    conventa.actualizarVenta(modventa.getIdfactura());
                }

            }

        });
    }

    public boolean validacioncorreo(String correo) {
        String validacion = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Z|a-z]{2,}$";
        Pattern validar = Pattern.compile(validacion);
        Matcher cor = validar.matcher(correo);

        return cor.matches();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == (prin.getBtnNuevoUsuario())) {
            usu.iniciarUsuario();
            prin.setVisible(false);

        }

        if (e.getSource() == (prin.getBtnNuevoCliente())) {
            clic.iniciarCliente();
            prin.setVisible(false);

        }

        if (e.getSource() == (prin.getBtnAñadirProveedor())) {
            proc.iniciarProveedor();
            prin.setVisible(false);

        }

        if (e.getSource() == (prin.getBtnNuevoProducto())) {
            prin.setVisible(false);
            conpro.iniciarProducto();

        }

        if (e.getSource() == (prin.getBtnNuevaFactura())) {
            controlfaccom.iniciarFactura_Compra();
            prin.setVisible(false);
        }

        if (e.getSource() == (prin.getBtnNuevaVenta())) {
            conventa.iniciarVenta();
            prin.setVisible(false);
        }

        if (e.getSource() == (detallefactu.getBtnBuscarProducto())) {
            tableproducto.setVisible(true);
            tableproducto.setLocationRelativeTo(null);
            detallefactu.setVisible(false);
            prin.setVisible(false);
            modproduc.mostrarTablaProducto(tableproducto.getJtproducto(), "", "");

            tableproducto.addWindowListener(new WindowAdapter() {
                ;
        public void windowClosed(WindowEvent e) {
                    tableproducto.setVisible(false);
                    detallefactu.setVisible(true);
                }
            });

            tableproducto.getTxtFiltro().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableproducto.getTxtFiltro().setText("");
                    tableproducto.getTxtFiltro().setForeground(Color.black);
                }

            });

            tableproducto.getJtproducto().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int fila = tableproducto.getJtproducto().rowAtPoint(e.getPoint());
                    int columna = tableproducto.getJtproducto().columnAtPoint(e.getPoint());
                    modproduc.setIdpro(Integer.parseInt(tableproducto.getJtproducto().getValueAt(fila, 0).toString()));
                    if (columna == 6) {
                        tableproducto.setVisible(false);
                        detallefactu.setVisible(true);
                        Object idproducto = modproduc.getIdpro();
                        detallefactu.getTxtidproducto().setText(idproducto.toString());
                        JOptionPane.showMessageDialog(null, "Producto Agregado");
                    }

                }

            });
        }

        if (e.getSource().equals(detallefactu.getBtnagregardetallefactura())) {

            modfactucom.setIdprodu(Integer.parseInt(detallefactu.getTxtidproducto().getText()));
            modfactucom.setCantidadcompra(Integer.parseInt(detallefactu.getTxtcantidadcomprada().getText()));
            modfactucom.setPreciouni(Integer.parseInt(detallefactu.getTxtpreciounitario().getText()));
            modfactucom.insertarfacturacompraproducto();
            modfactucom.mostrarDetalleFactura(detallefactu.getJtDetalleFactura(), "", "");

        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int seleccion = prin.getJPPrincipal().getSelectedIndex();
        if (seleccion == 0) {
            gestionarUsuario();
        }

        if (seleccion == 2) {
            gestionarCliente();

        }

        if (seleccion == 1) {
            gestionarProveedor();

        }

        if (seleccion == 3) {
            gestionarProducto();

        }

        if (seleccion == 4) {
            gestionarFactura_Compra();

        }

        if (seleccion == 5) {
            gestionarVenta();

        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        modusu.mostrarTablaUsuario(prin.getJtUsuario(), prin.getTxtFiltro().getText(), "Usuario");
        modcli.mostrarTablaCliente(prin.getJtCliente(), prin.getTxtFiltroCliente().getText(), "Cliente");
        modpro.mostrarTablaProvedor(prin.getJtProvedor(), prin.getTxtFiltroProvedor().getText(), "Provedor");
        modproduc.mostrarTablaProducto(prin.getJtProducto(), prin.getTxtFiltroProducto().getText(), "Producto");
        modfactucom.mostrarFactura_Compra(prin.getJtFactura(), prin.getTxtFiltroFactura().getText(), "Factura");
        modventa.mostrarVentaTabla(prin.getJtVenta(), prin.getTxtFiltroVenta().getText(), "Venta");
        modproduc.mostrarTablaProducto(tableproducto.getJtproducto(), tableproducto.getTxtFiltro().getText(), "");

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        modusu.mostrarTablaUsuario(prin.getJtUsuario(), prin.getTxtFiltro().getText(), "Usuario");
        modcli.mostrarTablaCliente(prin.getJtCliente(), prin.getTxtFiltroCliente().getText(), "Cliente");
        modpro.mostrarTablaProvedor(prin.getJtProvedor(), prin.getTxtFiltroProvedor().getText(), "Provedor");
        modproduc.mostrarTablaProducto(prin.getJtProducto(), prin.getTxtFiltroProducto().getText(), "Producto");
        modfactucom.mostrarFactura_Compra(prin.getJtFactura(), prin.getTxtFiltroFactura().getText(), "Factura");
        modventa.mostrarVentaTabla(prin.getJtVenta(), prin.getTxtFiltroVenta().getText(), "Venta");
        modproduc.mostrarTablaProducto(tableproducto.getJtproducto(), tableproducto.getTxtFiltro().getText(), "");

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        modusu.mostrarTablaUsuario(prin.getJtUsuario(), prin.getTxtFiltro().getText(), "Usuario");
        modcli.mostrarTablaCliente(prin.getJtCliente(), prin.getTxtFiltroCliente().getText(), "Cliente");
        modpro.mostrarTablaProvedor(prin.getJtProvedor(), prin.getTxtFiltroProvedor().getText(), "Provedor");
        modproduc.mostrarTablaProducto(prin.getJtProducto(), prin.getTxtFiltroProducto().getText(), "Producto");
        modfactucom.mostrarFactura_Compra(prin.getJtFactura(), prin.getTxtFiltroFactura().getText(), "Factura");
        modventa.mostrarVentaTabla(prin.getJtVenta(), prin.getTxtFiltroVenta().getText(), "Venta");
        modproduc.mostrarTablaProducto(tableproducto.getJtproducto(), tableproducto.getTxtFiltro().getText(), "");

    }
}
